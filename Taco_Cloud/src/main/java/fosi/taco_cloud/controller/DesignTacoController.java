package fosi.taco_cloud.controller;

import fosi.taco_cloud.entity.Ingredient;
import fosi.taco_cloud.entity.Ingredient.Type;
import fosi.taco_cloud.entity.Taco;
import fosi.taco_cloud.entity.TacoOrder;
import fosi.taco_cloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController
{

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientToModel(Model model)
    {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        for (Type type : Type.values())
        {
            model.addAttribute(type.toString().toLowerCase(), filterByType(Streamable.of(ingredients).toList(), type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order()
    {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco()
    {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm()
    {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder)
    {
        if (errors.hasErrors())
        {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private static Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type)
    {
        return ingredients.stream()
            .filter(i -> i.getType().equals(type))
            .collect(Collectors.toList());
    }
}
