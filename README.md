# CS157A
# SQL queries for the tasks
1. Task 1: (Registered user report information about the registered users)
           SELECT * FROM user
           
2. Task 2: (Insert a new customer)
           INSER INTO user(user_id, username, password, full_name, address, email, phone)
           VALUES (‘107’,’Susan’,’Susan Cortez’,’564 Washington St, San Jose, CA 95113’,’susan@gmail.com’,4084718369)

3. Task 3: (descriptiona and query)

4. Task 4: Sales report: report the total of sales by the month and year
           SELECT  year(order_date) as SaleYear,month(order_date) as SaleMonth,Sum(total_cost) as TotalSales
           FROM Orders
           GROUP BY year(order_date), month(order_date)
           ORDER BY year(order_date), month(order_date)
           
5. Task 5:
6. Task 6:
7. Task 7:
8. Task 8: Common product in shopping cart: Report the common product in the shopping carts of Customers now for marketing purposes
          SELECT P.product_id, Op.option_id,product_name, option_name, Sum(CHP.quantity) as TotalQuantity
          FROM Product P
	             INNER JOIN Carts_Has_Products CHP ON P.product_id = CHP.product_id
               INNER JOIN Options Op ON CHP.option_id = Op.option_id
          GROUP BY P.product_id, Op.option_id
          
9. Task 9:

10. Task 10: (Functionality: Inventory report: get a report about the counts of items in the inventory.
(When we design product and option product, we don’t design expiration date for the product. So, I can’t find the inventory)
SQL: Inventory report: display all products which have quantity > 50 )
          SELECT P.product_id, Op.option_id,product_name, option_name, quantity
          FROM Product P,
	             Products_Has_Options CHO,
	             Options Op 
          WHERE P.product_id = CHO.product_id AND CHO.option_id = Op.option_id AND quantity >30
11. Task 11:
12. Task 12:
13. Task 13:

14. Task 14: (Inventory report: get a report about items out of stock with vendor information to reorder. )
          SELECT P.product_id, Op.option_id,product_name, option_name, quantity
          FROM Product P,
	             Products_Has_Options CHO,
               Options Op 
          WHERE P.product_id = CHO.product_id AND CHO.option_id = Op.option_id AND quantity <0







