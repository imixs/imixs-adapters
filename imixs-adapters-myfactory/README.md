# Imixs Adapter MyFactory

The **Imixs-Adapter-MyFactory** module provides a connector to integrate the [MyFactory ERP system](https://www.myfactory.com/) into the Imixs-Workflow engine. This adapter allows you to search and retrieve and update data from MyFactory via its SOAP API.

The Imixs-Adapter-MyFactory module wraps the MyFactory SOAP API into easy-to-use Jakarta EE services:

| Service                   | Description                                                  |
| ------------------------- | ------------------------------------------------------------ |
| `MyFactorySessionManager` | Handles authentication (login/logout) and session management |
| `CustomerService`         | Search and retrieve customer data                            |
| `ContactService`          | Search and retrieve contact persons                          |
| `SalesOrderService`       | Update Sales Orders                                          |

## Maven

Add the following dependency to your project:

```xml
<dependency>
    <groupId>org.imixs.workflow</groupId>
    <artifactId>imixs-adapters-myfactory</artifactId>
    <version>3.2.2</version>
</dependency>
```

## Configuration

The adapter requires the MyFactory server endpoint and credentials.
To setup the Imixs-MyFactory Adapter the following environment variables must be set:

| Variable                    | Description                           | Example                                            |
| --------------------------- | ------------------------------------- | -------------------------------------------------- |
| MYFACTORY_ENDPOINT_URL_AUTH | The MyFactory authentication endpoint | hhttp://localhost/myfactory/services/services.asmx |
| MYFACTORY_USERNAME          | Userid for authentication             |                                                    |
| MYFACTORY_PASSWORD          | Userid password                       |                                                    |
| MYFACTORY_DATABASE          | Database name to connect              |                                                    |
| MYFACTORY_DEVISION          | Devision to connect                   | default = 1                                        |

The following example shows a setup for in a Docker Compose file running in a local dev environment:

```yaml
    ....
      my-app:
        image: imixs/imixs-office-workflow
        environment:
          ....
          MYFACTORY_ENDPOINT_URL_AUTH: "http://myfactory.foo.com/myfactory/services/services.asmx"
          MYFACTORY_USERNAME: "test"
          MYFACTORY_PASSWORD: "*****"
          MYFACTORY_DATABASE: "mydb"
          MYFACTORY_DEVISION: "1"
        ....
        ports:
          - "8080:8080"
    ....
```

The individual services (CustomerService, ContactService) automatically compute their specific endpoints from the base URL.

## Usage

### Session Management

All API calls require an authenticated session. The `MyFactorySessionManager` handles login and logout:

```java
@Inject
MyFactorySessionManager sessionManager;

// Login before API calls
sessionManager.login();

try {
    // ... perform API operations ...
} finally {
    // Always logout to release the session
    sessionManager.logout();
}
```

### Customer Search

The `CustomerService` provides methods to search for customers:

```java
@Inject
CustomerService customerService;

// Search by company name (use * as wildcard)
List<Customer> customers = customerService.searchByCompanyName("Müller*");

// Search by customer number
List<Customer> customers = customerService.searchByCustomerNumber("10001");

// Search by city
List<Customer> customers = customerService.searchByCity("München");

// Search by matchcode
List<Customer> customers = customerService.searchByMatchcode("MUEL");

// Combined search with multiple criteria
CustomerSearchCondition condition = new CustomerSearchCondition()
    .setName1("Müller*")
    .setCity("München")
    .setCustomerGroup("A");
List<Customer> customers = customerService.searchCustomers(condition);

// Get single customer by ID
Customer customer = customerService.getCustomerById(12345);
```

### Customer Entity

The `Customer` class contains the most important fields:

```java
Customer customer = customers.get(0);

// Customer-specific fields
int customerId = customer.getCustomerId();           // Internal ID (use for orders)
String customerNumber = customer.getCustomerNumber(); // Customer number
String customerGroup = customer.getCustomerGroup();

// Address fields
String company = customer.getName1();      // Company name
String name2 = customer.getName2();        // Additional name line
String street = customer.getStreet();
String postalCode = customer.getPostalCode();
String city = customer.getCity();
String country = customer.getCountry();
String phone = customer.getPhoneNr();
String email = customer.getEmail();

// Display helper
String display = customer.getDisplayName(); // e.g. "[10001] Müller GmbH (München)"
```

### Contact Search

The `ContactService` provides methods to search for contact persons:

```java
@Inject
ContactService contactService;

// Search by last name
List<Contact> contacts = contactService.searchContactsByLastName("Schmidt");

// Search by email
List<Contact> contacts = contactService.searchContactsByEmail("info@example.com");

// Search by first and last name
List<Contact> contacts = contactService.searchContactsByName("Hans", "Schmidt");

// Get all contacts for an address
List<Contact> contacts = contactService.getContactsByAddressId(12345);

// Combined search
ContactSearchCondition condition = new ContactSearchCondition()
    .setLastName("Schmidt")
    .setPosition("Geschäftsführer");
List<Contact> contacts = contactService.searchContacts(condition);
```

## Wildcards

MyFactory supports `*` as a wildcard character for partial matching:

```java
// Find all companies starting with "Müller"
customerService.searchByCompanyName("Müller*");

// Find all companies containing "Tech"
customerService.searchByCompanyName("*Tech*");

// Find all companies ending with "GmbH"
customerService.searchByCompanyName("*GmbH");
```

## Example: Integration with Imixs-Workflow

A typical use case is to search for a customer during workflow processing:

```java
@Named
@RequestScoped
public class MyFactoryLookupController {

    @Inject
    MyFactorySessionManager sessionManager;

    @Inject
    CustomerService customerService;

    public List<Customer> searchCustomers(String searchTerm) {
        List<Customer> result = new ArrayList<>();
        try {
            sessionManager.login();
            result = customerService.searchByCompanyName(searchTerm + "*");
        } catch (Exception e) {
            logger.warning("MyFactory search failed: " + e.getMessage());
        } finally {
            try {
                sessionManager.logout();
            } catch (Exception e) {
                // ignore
            }
        }
        return result;
    }
}
```

## JUnit Testing

For unit testing without CDI, use the setter methods:

```java
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService customerService;
    private MyFactorySessionManager sessionManager;

    @BeforeEach
    void setUp() throws Exception {
        // Build SessionManager with explicit configuration
        sessionManager = new MyFactorySessionManager(
                "http://your-server/myfactory/services/services.asmx",
                "USERNAME",
                "PASSWORD",
                "DATABASE",
                1);  // division

        // Create service and inject dependencies manually
        customerService = new CustomerService();
        customerService.setSessionManager(sessionManager);
    }

    @Test
    void testSearchCustomers() throws Exception {
        try {
            sessionManager.login();

            List<Customer> customers = customerService.searchByCompanyName("Test*");

            assertFalse(customers.isEmpty());
            customers.forEach(c -> {
                System.out.println(c.getDisplayName());
            });

        } finally {
            sessionManager.logout();
        }
    }
}
```

## Contributing

Contributions are welcome! If you need additional MyFactory operations (e.g., creating orders, managing suppliers), feel free to open an issue or submit a pull request.
