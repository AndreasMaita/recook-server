package com.recook.controller;

import com.recook.exception.RessourceNotFoundException;
import com.recook.model.ShoppingItem;
import com.recook.model.ShoppingList;
import com.recook.repository.ShoppingItemRepository;
import com.recook.repository.ShoppingListRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin( origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ShoppingListController {
    @Autowired
    ShoppingItemRepository itemRepo;

    @Autowired
    ShoppingListRepository listRepo;

    @GetMapping("/shoppingList")
    public List<ShoppingList> getAllShoppingLists() { return this.listRepo.findAll(); }

    @GetMapping("/shoppingList/{id}")
    public ResponseEntity<ShoppingList> getShoppingListId(@PathVariable( value = "id" ) Long listId) throws RessourceNotFoundException {
        ShoppingList list = listRepo.findById(listId)
        .orElseThrow( () -> new RessourceNotFoundException(" Keine Einkaufsliste gefunden mit dieser id :: " + listId));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/shoppingList")
    public ShoppingList createShoppingList(@Valid @RequestBody ShoppingList list) {
        return listRepo.save(list);
    }

    @DeleteMapping("/shoppingList/{id}")
    public Map<String, Boolean> deleteList(@PathVariable(value = "id") Long listId)
        throws RessourceNotFoundException {
        ShoppingList list = listRepo.findById(listId)
                .orElseThrow( () -> new RessourceNotFoundException("Einkaufsliste nicht gefunden mit dieser id :: " + listId));

        listRepo.delete(list);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
        }

}
