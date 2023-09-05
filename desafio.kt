enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

interface Usuario {
    val nome: String
}

interface ConteudoEducacional {
    val nome: String
    val duracao: Int
}

interface Formacao {
    val nome: String
    val conteudos: List<ConteudoEducacional>

    val inscritos: MutableList<Usuario>

    fun matricular(usuario: Usuario)
}

class BasicoUsuario(override val nome: String) : Usuario

class IntermediarioUsuario(override val nome: String) : Usuario

class DificilUsuario(override val nome: String) : Usuario

class ConteudoBasico(override val nome: String, override val duracao: Int = 60) : ConteudoEducacional

class ConteudoIntermediario(override val nome: String, override val duracao: Int = 60) : ConteudoEducacional

class ConteudoDificil(override val nome: String, override val duracao: Int = 60) : ConteudoEducacional

class FormacaoBasica(override val nome: String, override val conteudos: List<ConteudoBasico> = emptyList()) : Formacao {
    override val inscritos: MutableList<Usuario> = mutableListOf()

    override fun matricular(usuario: Usuario) {
        if (usuario !is BasicoUsuario) {
            throw IllegalArgumentException("Usuário deve ser do nível básico")
        }

        inscritos.add(usuario)
    }
}

class FormacaoIntermediaria(override val nome: String, override val conteudos: List<ConteudoIntermediario> = emptyList()) : Formacao {
    override val inscritos: MutableList<Usuario> = mutableListOf()

    override fun matricular(usuario: Usuario) {
        if (usuario !is IntermediarioUsuario) {
            throw IllegalArgumentException("Usuário deve ser do nível intermediário")
        }

        inscritos.add(usuario)
    }
}

class FormacaoDificil(override val nome: String, override val conteudos: List<ConteudoDificil> = emptyList()) : Formacao {
    override val inscritos: MutableList<Usuario> = mutableListOf()

    override fun matricular(usuario: Usuario) {
        if (usuario !is DificilUsuario) {
            throw IllegalArgumentException("Usuário deve ser do nível difícil")
        }

        inscritos.add(usuario)
    }
}

fun main() {
    val formacaoBasica = FormacaoBasica("Formação Básica")
    val formacaoIntermediaria = FormacaoIntermediaria("Formação Intermediária")
    val formacaoDificil = FormacaoDificil("Formação Difícil")

    val usuarioBasico = BasicoUsuario("Fulano")
    val usuarioIntermediario = IntermediarioUsuario("Beltrano")
    val usuarioDificil = DificilUsuario("Ciclano")

    formacaoBasica.matricular(usuarioBasico)
    formacaoIntermediaria.matricular(usuarioIntermediario)
    formacaoDificil.matricular(usuarioDificil)

    println("Formação básica:")
    println(formacaoBasica)
    println("Formação intermediária:")
    println(formacaoIntermediaria)
    println("Formação difícil:")
    println(formacaoDificil)
}
