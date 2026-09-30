package com.melek.ecommerce.catalog.product.application.port;

import java.util.List;

public interface ProductSearch {

    ProductSearchPage search(ProductSearchCriteria criteria);
}
