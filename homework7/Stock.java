/**
 * Stock class
 */
public class Stock{
    private String symbol; // The unique stock symbol (e.g., AAPL for Apple Inc.). This attribute will be used as the key for inserting nodes into the AVL tree and must be unique for each stock.
    private double price; //the current stock price
    private long volume; //the trading volume of the stock
    private long marketCap; //the market capitalization of the company
    /**
     * Constructor
     * @param symbol a unique stock symbol 
     * @param price  the current stock price
     * @param volume the trading volume of the stock
     * @param marketCap the market capitalization of the company
     */
    public Stock(String symbol, double price, long volume, long marketCap){
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }
    /**
     * Getter
     * @return symbol of the stock
     */
    public String getSymbol(){                  return symbol;              }
    /**
     * Getter
     * @return price of the stock
     */
    public double getPrice(){                   return price;               }
    /**
     * Getter
     * @return volume of the stock
     */
    public long getVolume(){                    return volume;              }
    /**
     * Getter
     * @return market capitalization of the company
     */
    public long getMarketCap(){                 return marketCap;           }
    /**
     * Setter
     * @param symbol of the stock
     */
    public void setSymbol(String symbol){       this.symbol = symbol;       }
    /**
     * Setter
     * @param price of the stock
     */
    public void setPrice(double price){         this.price = price;         }
    /**
     * Setter
     * @param volume of the stock
     */
    public void setVolume(long volume){         this.volume = volume;       }
    /**
     * Setter
     * @param marketCap of the company
     */
    public void setMarketCap(long marketCap){   this.marketCap = marketCap; }
    
    /**
     * @return a string representation of the stock
     */
    @Override
    public String toString(){
        return "Stock: " + symbol + " Price: " + price + " Volume: " + volume + " Market Cap: " + marketCap;
    }

}