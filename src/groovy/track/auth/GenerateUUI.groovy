package track.auth

import java.text.Normalizer


class GenerateUUI {
	String  generate (String firstname, String lastname) {
		
		def iduser = Normalizer.normalize(firstname, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")  + Normalizer.normalize(lastname, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + UUID.randomUUID()
		
		return iduser
		
	}
}
