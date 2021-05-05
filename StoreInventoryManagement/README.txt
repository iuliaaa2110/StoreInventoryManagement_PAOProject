StoreInventoryManagement

Services (Interogari):
Services:

1.  show_storehouse_stock = Show actual stock from the storeHouse 
2.  show_store_stock      = Show actual stock of a store 
3.  show_storehouse_bank  = Show the main bank (the bank from the Storehouse) 
4.  show_store_bank       = Show the bank's gain of a store 
5.  collect_gain          = Send the money from a store's bank to the StoreHouse's bank 
6.  refill                = Refill the stock at a store for specific products, inccreasing the stock to the desired quantity.(either to the regular quantity, either to a                                     specific quantity) 
7.  find_provider         = Find the provider with the best price for a product 
8.  order                 = Make an order to a provider 
9.  sell                  = Make a sell 
10. calculate_outprice    = Calculate the outprice for a product 

Classes:

OfferAndStock - Pair of BigDecimal and Integer
Product
Provider
StockManagement
StoreHouse
Store
   Supermarket
Service
