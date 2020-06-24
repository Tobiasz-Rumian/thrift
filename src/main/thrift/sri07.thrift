namespace java com.example
exception NotEnoughProductsException {
}
struct OrderItem {
  1:i32 id,
  2:i32 amount
}
service ProductCart {
  void placeOrder() throws (1:NotEnoughProductsException e)
  void addItem(1:OrderItem item)
  void updateItem(1:OrderItem item)
  void removeItem(1:i32 id)
}
struct Product {
  1:i32 id,
  2:string name,
  3:double price,
  4:i32 amountInStoreage
}

service ProductManager {
   list<Product> getAllProducts()
}



