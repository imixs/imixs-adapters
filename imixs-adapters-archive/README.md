# Imixs-Archive

The Imixs-Archive provides a API to store workitems into a Hadoop Cluster. Imixs-Archive uses the [WebHDFS Rest API](https://hadoop.apache.org/docs/r2.8.0/hadoop-project-dist/hadoop-hdfs/WebHDFS.html) to connect to a hadoop cluster.

## HDFSWebClient

The HDFSWebClient code is based on the workf of [zxs/webhdfs-java-client](https://github.com/zxs/webhdfs-java-client]. 

## JUnit Tests

The libray can be tested with a single node hadoop cluster. 
For all integration tests just start the Docker hadoop container with the following command:

	docker run --name="hadoop" -d -h my-hadoop-cluster.local -p 50070:50070 -p 50075:50075  imixs/hadoop

Make sure that the hostname 'my-hadoop-cluster.local' is listed in your local test environment

See the [Imixs-Docker hadoop project](https://github.com/imixs/imixs-docker/tree/master/hadoop) for mor details.


