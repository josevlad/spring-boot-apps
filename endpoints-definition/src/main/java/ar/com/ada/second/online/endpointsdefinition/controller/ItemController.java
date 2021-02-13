package ar.com.ada.second.online.endpointsdefinition.controller;

import ar.com.ada.second.online.endpointsdefinition.model.dto.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private List<ItemDTO> items = new ArrayList<>(Arrays.asList(
            new ItemDTO(1L, "CocaCola", "Bebida gasesa", 123456),
            new ItemDTO(2L, "Palitos Salados", "Snack", 123457),
            new ItemDTO(3L, "Cerveza Heineken", "Bebida rica", 123458)
    ));

    @GetMapping({ "/", "" })
    public ResponseEntity getItemsMethod() {

        return ResponseEntity.ok().body(items);
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postItemMethod(@Valid @RequestBody ItemDTO item) throws URISyntaxException {
        //Long id = Math.abs(new Random().nextLong()) % 100;

        //long itemsCounter = items.stream().count();
        //ItemDTO lastItem = items.stream()
        // .skip(itemsCounter - 1)
        // .findFirst()
        // .orElse(null);

        ItemDTO lastItem = items.stream()
                .reduce((first, second) -> second)
                .orElse(null);

        Long id = (lastItem == null)
                ? 1
                : lastItem.getId() + 1;

        item.setId(id);

        items.add(item);

        URI uri = new URI("/items/" + id);
        return ResponseEntity.created(uri).body(item);
    }

    @GetMapping({ "/{itemId}", "/{itemId}/" })
    public ResponseEntity getItemByIdMethod(@PathVariable Long itemId) {

//        ItemDTO itemById = null;
//
//        for (int i = 0; i < items.size(); i++) {
//            if (items.get(i).getId().equals(itemId)) {
//                itemById = items.get(i);
//            }
//        }

        ItemDTO itemById = items.stream()
                // .filter((item) -> item.getId().equals(itemId))
                .filter((item) -> {
                    return item.getId().equals(itemId);
                })
                .findFirst()
                .orElse(null);

//        HttpStatus httpStatus = null;
//
//        if (itemById == null) {
//            httpStatus = HttpStatus.NOT_FOUND;
//        } else {
//            httpStatus = HttpStatus.OK;
//        }

        HttpStatus httpStatus = (itemById == null)
                ? HttpStatus.NOT_FOUND
                : HttpStatus.OK;

        return ResponseEntity.status(httpStatus).body(itemById);
    }

    @DeleteMapping({ "/{itemId}", "/{itemId}/" })
    public ResponseEntity deleteItemByIdMethod(@PathVariable Long itemId) {

        Boolean hasDelete = items.removeIf(item -> item.getId().equals(itemId));

        Map<String, String> itemNotFound = new HashMap<>();
        itemNotFound.put("error", HttpStatus.BAD_REQUEST.toString());
        itemNotFound.put("message", "item id not exist");

        return (hasDelete)
                ? ResponseEntity.noContent().build() // 204
                : ResponseEntity.badRequest().body(itemNotFound); // 400
    }
}
