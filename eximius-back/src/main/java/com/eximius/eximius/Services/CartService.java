package com.eximius.eximius.Services;

import com.eximius.eximius.DTO.CartItemDto;
import com.eximius.eximius.Entities.Cart;
import com.eximius.eximius.Entities.CartItem;
import com.eximius.eximius.Entities.Product;
import com.eximius.eximius.Repositories.CartRepository;
import com.eximius.eximius.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addProductToCart(CartItemDto newItemDTO) {
        // Encontrar el carrito
        Cart cart = cartRepository.findById(newItemDTO.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Encontrar el producto
        Product product = productRepository.findById(newItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Crear un nuevo CartItem
        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(newItemDTO.getQuantity());
        newItem.setPrice(product.getPrice());
        newItem.setCart(cart);

        // Añadir el nuevo ítem al carrito
        cart.getCartItems().add(newItem);

        // Recalcular el total del carrito
        calculateTotalPrice(cart);

        // Guardar el carrito actualizado
        return cartRepository.save(cart);
    }

    public void calculateTotalPrice(Cart cart) {
        double total = cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(total);
    }

    // Otros métodos relacionados con el carrito
}

