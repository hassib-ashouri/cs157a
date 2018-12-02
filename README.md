# CS157A
# SQL queries for the tasks
1. Task 1: (Registered user report information about the registered users)
           SELECT * FROM user
           
2. Task 2: (Insert a new customer)
           INSER INTO user(user_id, username, password, full_name, address, email, phone)
           VALUES (‘107’,’Susan’,’Susan Cortez’,’564 Washington St, San Jose, CA 95113’,’susan@gmail.com’,4084718369)

3. Task 3: (Display a list of the customers that don’t buy at all.)
	   SELECT * 
	   FROM User
	   WHERE NOT EXISTS ( SELECT * 
			      FROM Orders_Placed_User OP
                              WHERE User.user_id = OP.user_id)
                                        
                                        

4. Task 4: (Sales report: report the total of sales by the month and year )
           SELECT  year(order_date) as SaleYear,month(order_date) as SaleMonth,Sum(total_cost) as TotalSales
           FROM Orders
           GROUP BY year(order_date), month(order_date)
           ORDER BY year(order_date), month(order_date)
           
5. Task 5:

6. Task 6:
                                
7. Task 7: (Display a report of products on sale.)
	 SELECT P.product_id as ProductID, product_name as ProductName, O.option_id as OptionID,O.option_name as OptionName, OP.quantity as Quantity, on_sale, (OP.quantity * PHO.price) as TotalPrice
   	   FROM Product P, Options O, Products_Has_Options PHO, Orders_Has_Products OP
	   WHERE P.product_id = PHO.product_id 
	   	AND O.option_id = PHO.option_id 
	   	AND  P.product_id = OP.Product_id 
	   	AND on_sale = 1
	   
8. Task 8: (Common product in shopping cart: Report the common product in the shopping carts of Customers now for marketing purposes. )
          SELECT P.product_id, Op.option_id,product_name, option_name, Sum(CHP.quantity) as TotalQuantity
          FROM Product P
	       INNER JOIN Carts_Has_Products CHP ON P.product_id = CHP.product_id
               INNER JOIN Options Op ON CHP.option_id = Op.option_id
          GROUP BY P.product_id, Op.option_id
          
9. Task 9:(Insert a new product)
        INSERT INTO Product(product_id, name, description) 
		VALUES ("1401", "Samsung","The iPhone X display so immersive the device itself disappears the experience.");
	INSERT INTO Products_Has_Options(product_id,option_id,quantity,price,on_sale,specs) 
		VALUES('1401','1201','20','899','0','Iphone XS Storage capacity 256 GB') ;
	INSERT INTO Products_Sold_Vendor(vendor_id,product_id) 
		VALUES('140','400') ;
	INSERT INTO Products_Belong_Category(product_id,category_id)
		VALUES('140','400')
	   

10. Task 10: (Functionality: Inventory report: get a report about the counts of items in the inventory.
(When we design product and option product, we don’t design expiration date for the product. So, I can’t find the inventory)
SQL: Inventory report: display all products which have quantity > 50 )
          SELECT P.product_id, Op.option_id,product_name, option_name, quantity
          FROM Product P,
	             Products_Has_Options CHO,
	             Options Op 
          WHERE P.product_id = CHO.product_id AND CHO.option_id = Op.option_id AND quantity >30
	  
11. Task 11:(Update information regarding a specific product)

	UPDATE Product
   	SET product_name = 'Macbook Pro (2018)'
	WHERE Id = 1200
	
	
12. Task 12: (Display the number of product is each category )
	SELECT PC.category_id, category_name,Product.product_id, product_name
	FROM Product, Products_Belong_Category PC, Category
	WHERE PC.product_id = Product.product_id AND PC.category_id = Category.category_id AND Category.category_id = 4000
13. Task 13:

14. Task 14: (Inventory report: get a report about items out of stock with vendor information to reorder. )
          SELECT P.product_id, Op.option_id,product_name, option_name, quantity
          FROM Product P,
	             Products_Has_Options CHO,
               Options Op 
          WHERE P.product_id = CHO.product_id AND CHO.option_id = Op.option_id AND quantity <0







