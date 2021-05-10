"use strict";

IMIXS.namespace("org.imixs.workflow.wopi");

/**
 * Imixs-Wopi Adapter Script for the wopi integration
 * 
 * We register a message listener to react on messages send form the Editor to the host.
 * We send the 'Host_PostmessageReady' message to signal the Editor that we want receive all action messages.
 * 
 * We also customize the toolbar in the following way: 
 *  - hide the defult save command
 *  - add a custom save button with 'notify=true'
 *  - add a custom close button with 'notify=true'
 *  - privide event listener methods for the custom save/close actions
 * 
 */


// add a event listner to receife messages from the Wopi Editor
$(document).ready(function() {
	// Install the wopi message listener.
	// receive messages form libreoffice online
	window.addEventListener("message", receiveMessage, false);
});


// send a message to the Editor to customize the behaviour
function post(msg) {
	//var el = document.getElementById('libreoffice_online-viewer');
	var iframe = document.getElementById('libreoffice_online-viewer');
	iframe = iframe.contentWindow || (iframe.contentDocument.document || iframe.contentDocument);
				
	iframe.postMessage(JSON.stringify(msg), '*');
}

// Custom save method with 'Notify=true'. This action allows a tight UI integration.
/*function saveAction() {
	post({
		'MessageId': 'Action_Save',
		'Values': { 'Notify': true }
	});
}*/

// Customize the editor toolbar. This method is called after the 
// editor was initalized. 
function customizeToolbar() {
	// hide default UI_save button
	post({
		'MessageId' : 'Hide_Button',
		'Values' : {
			'id' : "save"
		}
	});
	// insert custom close button
	post({
		'MessageId' : 'Insert_Button',
		'Values' : {
			'id': 'imixs.close',
			'imgurl': 'http://localhost:9980/loleaflet/6a844e4/images/close_toolbar.svg',
		    'hint': 'Close Editor',
		    'mobile': true,
		    'label': 'close',
		    'insertBefore': 'imixs.save'
		}
	});		
	// insert custom save button
	post({
		'MessageId' : 'Insert_Button',
		'Values' : {
			'id': 'imixs.save',
			'imgurl': 'http://localhost:9980/loleaflet/6a844e4/images/lc_save.svg',
		    'hint': 'Save',
		    'mobile': true,
		    'label': 'save',
		    'insertBefore': 'Undo'
		}
	});
	
}

// Receive Editor Messages
// This function is invoked when the editor posts a message back.
// We react on 'Document_Loaded' to send a Host_PostmessageReady and we react on 
// Save and Custom Click events
function receiveMessage(event) {
	console.log('==== framed.doc.html receiveMessage: ' + event.data);
	var msg = JSON.parse(event.data);
	if (!msg) {
		return;
	}
	if (msg.MessageId == 'App_LoadingStatus') {
		if (msg.Values) {
			if (msg.Values.Status == 'Document_Loaded') {
				console.log('==== Document loaded we will inform the wopi client that we are ready!');
				var iframe = document.getElementById('libreoffice_online-viewer');
				iframe = iframe.contentWindow || (iframe.contentDocument.document || iframe.contentDocument);

				iframe.postMessage(JSON.stringify({ 'MessageId': 'Host_PostmessageReady' }), '*');
				//window.frames[0].postMessage(JSON.stringify({ 'MessageId': 'Host_PostmessageReady' }), '*');
				console.log('==== Host_PostmessageReady message send');
				
				// try to customize the toolbar
				customizeToolbar();
			}
		}
	// custom click events
	} else if (msg.MessageId == 'Clicked_Button') {
		if (msg.Values && msg.Values.Id=="imixs.save") {
			console.log('====  imixs.save');
			post({
				'MessageId': 'Action_Save',
				'Values': { 'Notify': true }
			});
			//save();
		} else if (msg.Values && msg.Values.Id=="imixs.close") {
			console.log('====  imixs.close');
			imixsWopi.closeViewer();
		}
	// action save completed 
	} else if (msg.MessageId == 'Action_Save_Resp') {
		if (msg.Values) {
			if (msg.Values.success == true) {
				console.log('==== Saved');
				imixsWopi.closeViewer();
				if (wopiControllerUpdateFile) {
					wopiControllerUpdateFile();
				}
			} else {
				console.log('==== Error during save');
			}
		}
	}
}


// The imixsWopi core module
// Provide method for UI integration
IMIXS.org.imixs.workflow.wopi = (function() {
	if (!IMIXS.org.imixs.core) {
		console.error("ERROR - missing dependency: imixs-core.js");
	}

	var imixs = IMIXS.org.imixs.core,

		formID = "",
		viewerID = "",

		// switch to iframe mode and load editor
		openViewer = function(ref) {
			var wopiuri = ref;
			var imixsform = $('#' + imixsWopi.formID);
			var libreoffice = $('#' + imixsWopi.viewerID);
			// show iframe...
			imixsform.hide();
			libreoffice.show();
			// reset iframe
			
			// hack.....
			wopiuri = wopiuri.replace("libreoffice-app", "localhost");
			
			buildViewer(imixsWopi.viewerID,wopiuri);
			
			var form = $('#libreoffice_online-viewer').contents().find('#libreoffice-form');
			form.submit();
		},


		/*
		 * This helper method builds a ifram with a form at a given 
		 * element. This is used to show the LibreOffice editor later
		 */
		buildViewer = function(elementid, actionuri) {
			var iframeElement = $("#" + elementid);
			$(iframeElement).empty();
			// build iframe....
			var content = '<iframe id="libreoffice_online-viewer" src="" width="100%" height="1000"></iframe>';
			$(iframeElement).append(content);
			var iframe = document.getElementById('libreoffice_online-viewer');
			iframe = iframe.contentWindow || (iframe.contentDocument.document || iframe.contentDocument);
			iframe.document.open();
			iframe.document.write('<html><body><form action="'+actionuri+'" enctype="multipart/form-data" method="post" id="libreoffice-form" style="display:none;"><input name="dummy" value="" type="hidden" id="dummy"/> <input type="submit" value="Load..." /></form></body></html>');
			iframe.document.close();
		},

		// close the libreoffice viewer and show the form part again
		closeViewer = function() {
			var imixsform = $('#' + imixsWopi.formID);
			var libreoffice = $('#' + imixsWopi.viewerID);
			$(libreoffice).empty();
			// show iframe...
			libreoffice.hide();
			imixsform.show();
		};


	// public API
	return {
		openViewer: openViewer,
		closeViewer: closeViewer,
		formID: formID,
		viewerID: viewerID
	};

}());

// Define public namespace
var imixsWopi = IMIXS.org.imixs.workflow.wopi;

