# Swiftbird

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c1dab84ef24b49fa9337eaec4cea5aa3)](https://app.codacy.com/gh/LeafClient/Swiftbird?utm_source=github.com&utm_medium=referral&utm_content=LeafClient/Swiftbird&utm_campaign=Badge_Grade_Dashboard)

Swiftbird is a library created for **Leaf** that allows the creation of what we call 
"Property" _(a.k.a Setting)_.  
The default implementation wrote by [Shyrogan](https://github.com/Shyrogan) is annotation-based
(see the [Default Implementation](#defaultImplementation) section).

<a name="defaultImplementation"></a>
## Default implementation

### Declaring some properties

Declaring a property works with annotation:
````java
class Configuration {

    @Property(value = "Cheese", description = "It adds cheese, I guess?")
    private boolean cheese = false;

}
````  
will tell Swiftbird to considere the ``cheese`` field as a property.

### Retrieving your properties

In order to retrieve your properties, you have to create a ``Swiftbird`` instance using the `create()` method.  
Then, you can get the properties in their [PropertyContainer](https://github.com/LeafClient/Swiftbird/blob/master/src/main/java/com/leafclient/swiftbird/PropertyContainer.java) form.

````java
class Main {

    private final Swiftbird swiftbird = Swiftbird.create();

    public static void main(String[] args) {
        List<PropertyContainer<?>> properties = swiftbird.in(new Configuration());
    }

}
````