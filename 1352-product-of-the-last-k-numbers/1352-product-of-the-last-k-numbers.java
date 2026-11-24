import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList();
        prefixProducts.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            prefixProducts = new ArrayList();
            prefixProducts.add(1);
        } else {
            int lastProduct = (Integer) prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int size = prefixProducts.size();
        if (k >= size) {
            return 0;
        }
        int totalProduct = (Integer) prefixProducts.get(size - 1);
        int divisor = (Integer) prefixProducts.get(size - 1 - k);
        return totalProduct / divisor;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna