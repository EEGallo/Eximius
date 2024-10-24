package com.eximius.eximius.Controllers;


import com.eximius.eximius.DTO.CartItemDto;
import com.eximius.eximius.Entities.CartItem;
import com.eximius.eximius.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.eximius.eximius.Entities.Cart;
import com.eximius.eximius.Repositories.CartRepository;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    // Obtener todos los carritos
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Crear un nuevo carrito
    @PostMapping("/addproduct")
    public Cart addProductToCart(@RequestBody CartItemDto newItemDTO) {
        return cartService.addProductToCart(newItemDTO);
    }

    // Add a Product to Cart


    // Obtener un carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable Long id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cartRepository.delete(cart);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
