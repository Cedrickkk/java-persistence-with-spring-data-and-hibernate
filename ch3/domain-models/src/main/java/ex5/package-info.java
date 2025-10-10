@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name = "findItemsOrderByName",
                query = "SELECT i from Item order by i.name asc"
        ),
        @org.hibernate.annotations.NamedQuery(
                name = "findItemBuyNowPriceGreaterThan",
                query = "select i from Item i where i.buyNowPrice > :price",
                timeout = 60, // Seconds!
                comment = "Custom SQL comment"
        )
})

package ex5;
