package HRServices.Enums;

public enum EmployeeDivision {
    WAREHOUSE("Warehouse", "Warehouse operations and inventory."),
    TRANSPORTATION("Transportation", "Transportation and logistics operations."),
    SALES("Sales", "Sales and customer account operations."),
    MARKETING("Marketing", "Marketing and communications operations."),
    ENGINEERING("Engineering", "Engineering and product development."),
    MAINTENANCE("Maintenance", "Maintenance and facilities operations.");

    private final String name;
    private final String description;

    EmployeeDivision(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static EmployeeDivision fromString(String value) {
        String text = value.trim();

        for (EmployeeDivision division : values()) {
            if (division.name().equalsIgnoreCase(text)
                    || division.getName().equalsIgnoreCase(text)) {
                return division;
            }
        }

        throw new IllegalArgumentException(
                "Unknown EmployeeDivision: " + value
        );
    }

    @Override
    public String toString() {
        return name;
    }
}