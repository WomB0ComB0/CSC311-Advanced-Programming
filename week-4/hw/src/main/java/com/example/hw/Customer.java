package com.example.hw;

/**
 * Represents a customer with a name and address.
 * Implements the Bean interface.
 */
class Customer implements Bean {
    private String name;
    private String address;

    /**
     * Default constructor for Customer. Initializes name and address to "".
     */
    Customer() {
        this.name = "";
        this.address = "";
    }

    /**
     * Constructs a Customer with a specified name and address.
     *
     * @param name    The name of the customer.
     * @param address The address of the customer.
     */
    Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * {@inheritDoc}
     * Returns "Customer" as the bean type.
     */
    @Override
    public String getBeanType() {
        return "Customer";
    }

    /**
     * {@inheritDoc}
     * Displays the customer's name and address.
     */
    @Override
    public void showInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Address: " + this.address);
    }

    /**
     * Gets the name of the customer.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name for the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the customer.
     *
     * @return The customer's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The new address for the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}