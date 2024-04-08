package es.talent.fujitsu.springboot.numromanos.controllers;

// NumeroRomanoController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import es.talent.fujitsu.springboot.numromanos.models.NumeroNatural;


@Controller
public class NumeroRomanoController {

	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("numeroNatural", new NumeroNatural());
        return "index";
    }

    @PostMapping("/convertir")
    public String convertirANumeroRomano(@Validated NumeroNatural numeroNatural, Model model) {
        int numero = numeroNatural.getNumero();
        String numeroRomano = "La conversion del numero "+numeroNatural.getNumero()+" a romano es" +convertToRoman(numero);
        model.addAttribute("numeroRomano", numeroRomano);
        
        return "index";
    }

    static String convertToRoman(int number) {
		if (number >= 1 && number <= 3999) {
			return convertirANumeroRomano(number);

		}

		System.out.print("Debe de introducir un numero en el rango de 1 a 3999");
		return null;

	}

	private static String convertirANumeroRomano(int numero) {
		String[] simbolos = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
		int[] valores = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
		
		//mas eficiancia que con un string normal
		StringBuilder resultado = new StringBuilder();

		// Comenzamos desde el número más grande (M) y vamos restándolo
		for (int i = valores.length - 1; i >= 0; i--) {
			while (numero >= valores[i]) {
				resultado.append(simbolos[i]);
				numero -= valores[i];
			}
		}
		return resultado.toString();
	}
}
