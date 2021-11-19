package ch.peter.einkaufsliste;
public class ShoppingPlan
{
    public String shopName, category, userID;

    public ShoppingPlan()
    {

    }

    public ShoppingPlan(String shopName, String category, String userID)
    {
        this.shopName = shopName;
        this.category = category;
        this.userID = userID;
    }

    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }


}
