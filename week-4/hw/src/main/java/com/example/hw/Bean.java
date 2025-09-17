package com.example.hw;

/**
 * Represents a generic bean managed by the IOCContainer.
 * All managed objects should implement this interface.
 */
interface Bean {
    /**
     * Returns the type of the bean (e.g., "Customer", "ShoppingCart").
     *
     * @return The bean type as a String.
     */
    String getBeanType();

    /**
     * Displays the information related to the bean's data.
     */
    void showInfo();
}