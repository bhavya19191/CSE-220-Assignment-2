package HRServices.Records;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public record EmployeeInfo(
        String employeeId,
        ContactInfo employeeContact,
        ContactInfo supervisorContact,
        EmploymentInfo employmentInfo,
        EmergencyContact[] emergencyContacts
) {

    public EmployeeInfo {
        emergencyContacts = emergencyContacts.clone();
    }

    @Override
    public EmergencyContact[] emergencyContacts() {
        return emergencyContacts.clone();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EmployeeInfo employee)) {
            return false;
        }

        return Objects.equals(employeeId, employee.employeeId)
                && Objects.equals(employeeContact, employee.employeeContact)
                && Objects.equals(supervisorContact, employee.supervisorContact)
                && Objects.equals(employmentInfo, employee.employmentInfo)
                && Arrays.equals(
                emergencyContacts,
                employee.emergencyContacts
        );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
                employeeId,
                employeeContact,
                supervisorContact,
                employmentInfo
        );

        result = 31 * result + Arrays.hashCode(emergencyContacts);

        return result;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    public String toString(int tabLevel) {
        if (tabLevel < 0) {
            throw new IllegalArgumentException(
                    "tabLevel must not be negative."
            );
        }

        String indent = "\t".repeat(tabLevel);
        String oneTab = "\t".repeat(tabLevel + 1);

        StringBuilder text = new StringBuilder();

        text.append(indent)
                .append("Employee ID: ")
                .append(employeeId)
                .append('\n');

        text.append(indent)
                .append("Employment:")
                .append('\n');

        text.append(
                employmentInfo.toString(tabLevel + 1)
        ).append('\n');

        text.append(indent)
                .append("Employee contact:")
                .append('\n');

        text.append(
                employeeContact.toString(tabLevel + 1)
        ).append('\n');

        text.append(indent)
                .append("Supervisor contact:")
                .append('\n');

        text.append(
                supervisorContact.toString(tabLevel + 1)
        ).append('\n');

        text.append(indent)
                .append("Emergency contacts: ")
                .append(emergencyContacts.length);

        for (int i = 0; i < emergencyContacts.length; i++) {
            text.append('\n')
                    .append(oneTab)
                    .append("Emergency contact ")
                    .append(i + 1)
                    .append(':')
                    .append('\n');

            text.append(
                    emergencyContacts[i].toString(tabLevel + 2)
            );
        }

        return text.toString();
    }

    public static class Builder {
        private String employeeId;
        private ContactInfo employeeContact;
        private ContactInfo supervisorContact;
        private EmploymentInfo employmentInfo;

        private ArrayList<EmergencyContact> emergencyContacts =
                new ArrayList<>();

        public Builder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder employeeContact(ContactInfo employeeContact) {
            this.employeeContact = employeeContact;
            return this;
        }

        public Builder supervisorContact(ContactInfo supervisorContact) {
            this.supervisorContact = supervisorContact;
            return this;
        }

        public Builder employmentInfo(EmploymentInfo employmentInfo) {
            this.employmentInfo = employmentInfo;
            return this;
        }

        public Builder emergencyContacts(
                ArrayList<EmergencyContact> emergencyContacts
        ) {
            this.emergencyContacts = emergencyContacts;
            return this;
        }

        public EmployeeInfo build() {
            EmergencyContact[] contacts =
                    emergencyContacts.toArray(
                            new EmergencyContact[
                                    emergencyContacts.size()
                                    ]
                    );

            return new EmployeeInfo(
                    employeeId,
                    employeeContact,
                    supervisorContact,
                    employmentInfo,
                    contacts
            );
        }
    }
}
