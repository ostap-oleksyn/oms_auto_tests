package com.softserveinc.edu.ita.enums;

/**
 * Created by 1 on 03.06.2015.
 */
public enum Roles {
    ADMINISTRATOR {
        @Override
        public String toString() {
            return "Administrator";
        }
    },

    CUSTOMER {
        @Override
        public String toString() {
            return "Customer";
        }
    },

    MERCHANDISER {
        @Override
        public String toString() {
            return "Merchandiser";
        }
    },

    SUPERVISOR {
        @Override
        public String toString() {
            return "Supervisor";
        }
    };

}



