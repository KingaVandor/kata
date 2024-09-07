# Assumptions

1. Products:
   2. the product info sent in the request is always correct and does not need validation (our customers are very honest and the FE gets all product info real time)
   3. the latest price of a given product applies to all baskets at checkout, and not the price at which the product was put into the basket
2. Basket operations:
   3. the FE might not be in sync with the basket content on the BE (e.g. request sent twice accidentally or the response got lost etc.) Consequently FE might request removal of items already not in the basket.
   4. after each addition or removal of an item, the BE sends the current content of the basket in the response to ensure eventual consistency.
5. Customers:
   6. given that we don't have a DB, we cannot save the basket content to a logged in customers's account; so simply sessionIds are used here.
7. Errors:
   8. handled by the API controller
     

    