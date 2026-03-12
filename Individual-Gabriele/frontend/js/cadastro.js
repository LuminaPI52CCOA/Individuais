document.getElementById('formUsuario').addEventListener('submit', async (event) => {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const data_nasc = document.getElementById('data_nasc').value;
    const senha = document.getElementById('senha').value;

    if (senha.length < 4 || senha.length > 8) {
        alert("A senha deve ter entre 4 e 8 caracteres.");
        return;
    }

    const dadosUsuario = {
        nome: nome,
        email: email,
        dataNasc: data_nasc,
        senha: senha
    };

    console.log("Enviando dados:", dadosUsuario);

    try {
        const response = await fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosUsuario)
        });

        if (response.status === 201) {
            window.location.href = 'login.html';

        } else {
            const erroTexto = await response.text();
            console.error("Erro do servidor:", erroTexto);
            alert(`Erro ao cadastrar. Status: ${response.status}. Verifique os dados.`);
        }

    } catch (error) {
        console.error("Erro na requisição (Catch):", error);
        alert("Erro técnico: O servidor API pode estar offline.");
    }
});