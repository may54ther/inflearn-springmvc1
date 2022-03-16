package hello.itemservice.domain.web;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //lombok
public class BasicItemController {

    private final ItemRepository itemRepository;

    // MEMO: 상품 목록
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // MEMO: 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //테스트용 데이터
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("새우깡", 1500, 3));
        itemRepository.save(new Item("감자깡", 1500, 5));
        itemRepository.save(new Item("옥수수깡", 2000, 5));
        itemRepository.save(new Item("고구마깡", 2000, 1));
    }
}
