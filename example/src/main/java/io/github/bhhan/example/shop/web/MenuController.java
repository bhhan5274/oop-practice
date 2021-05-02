package io.github.bhhan.example.shop.web;

import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.usecase.MenuService;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/menus")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/{menuId}/validateOrder")
    @ResponseStatus(HttpStatus.OK)
    public boolean validateOrder(@PathVariable Long menuId, @Param("name") String menuName, @RequestBody List<OptionGroup> optionGroups){
        try{
            menuService.validateOrder(menuId, menuName, optionGroups);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
