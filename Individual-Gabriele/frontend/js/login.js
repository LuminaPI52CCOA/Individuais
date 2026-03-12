document.getElementById('formUsuario').addEventListener('submit', async (event) => {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    const dadosLogin = {
        email: email,
        senha: senha
    };

    try {
        const response = await fetch('http://localhost:8080/usuarios/login', { 
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dadosLogin)
        });

        if (response.ok) { 
            const usuarioLogado = await response.json();
            
            localStorage.setItem('id_usuario_logado', usuarioLogado.id);
            
            window.location.href = 'avaliacao.html';
        } else if (response.status === 401 || response.status === 404) {
            alert("E-mail ou senha incorretos.");
        } else {
            alert("Erro ao realizar login. Status: " + response.status);
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro técnico ao conectar com o servidor.");
    }
});