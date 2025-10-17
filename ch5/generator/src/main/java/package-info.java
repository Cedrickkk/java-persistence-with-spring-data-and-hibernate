@org.hibernate.annotations.GenericGenerator(
        name = "CUSTOM_ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "JPWHSD_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "1000"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "allocation_size",
                        value = "1"
                )
        })

package ch5;