const API_BASE_URL = 'http://localhost:8080';

const formCadastro = document.getElementById('formCadastro');
const selectRaridade = document.getElementById('raridade');
const mensagemDiv = document.getElementById('mensagem');

document.addEventListener('DOMContentLoaded', () => {
  carregarRaridades();
  adicionarListenersValidacao();
});

async function carregarRaridades() {
  try {
    const response = await fetch(`${API_BASE_URL}/raridades`);
    
    if (!response.ok) {
      console.log('Não foi possível carregar as raridades da API');
      const raridadesPadrao = ['Super Raro', 'Ultra Raro', 'Secret Rare', 'Holográfica'];
      preencherRaridades(raridadesPadrao);
      return;
    }

    
    const raridades = await response.json();
    console.log('Raridades carregadas:', raridades);
    preencherRaridades(raridades);

  } catch (erro) {
    console.error('Erro ao carregar raridades:', erro);
    const raridadesPadrao = ['Super Raro', 'Ultra Raro', 'Secret Rare', 'Holográfica'];
    preencherRaridades(raridadesPadrao);
  }
}

function preencherRaridades(raridades) {
  raridades.forEach(raridade => {
    const option = document.createElement('option');
    option.value = raridade.raridade || raridade;
    option.textContent = raridade.raridade || raridade;
    selectRaridade.appendChild(option);
  });
}

function adicionarListenersValidacao() {
  const nome = document.getElementById('nome');
  const idade = document.getElementById('idade');
  const dataCadastro = document.getElementById('dataCadastro');
  const tipoJogador = document.querySelectorAll('input[name="tipoJogador"]');
  const decks = document.querySelectorAll('input[name="decks"]');
  const raridade = document.getElementById('raridade');

  nome.addEventListener('blur', () => validarNome(nome.value));
  idade.addEventListener('blur', () => validarIdade(idade.value));
  dataCadastro.addEventListener('blur', () => validarData(dataCadastro.value));
  
  tipoJogador.forEach(radio => {
    radio.addEventListener('change', () => validarTipoJogador());
  });

  decks.forEach(checkbox => {
    checkbox.addEventListener('change', () => validarDecks());
  });

  raridade.addEventListener('change', () => validarRaridade(raridade.value));
}

function validarNome(nome) {
  const erro = document.getElementById('erroNome');
  if (nome.trim() === '') {
    erro.textContent = 'Nome é obrigatório';
    return false;
  }
  if (nome.trim().length < 3) {
    erro.textContent = 'Nome deve ter pelo menos 3 caracteres';
    return false;
  }
  if (nome.length > 100) {
    erro.textContent = 'Nome não pode exceder 100 caracteres';
    return false;
  }
  erro.textContent = '';
  return true;
}

function validarIdade(idade) {
  const erro = document.getElementById('erroIdade');
  const idadeNum = parseInt(idade);
  
  if (isNaN(idadeNum)) {
    erro.textContent = 'Idade é obrigatória';
    return false;
  }
  if (idadeNum < 1 || idadeNum > 120) {
    erro.textContent = 'Idade deve estar entre 1 e 120';
    return false;
  }
  erro.textContent = '';
  return true;
}

function validarData(data) {
  const erro = document.getElementById('erroData');
  
  if (data === '') {
    erro.textContent = 'Data é obrigatória';
    return false;
  }

  const dataObj = new Date(data);
  const agora = new Date();
  
  if (dataObj > agora) {
    erro.textContent = 'Data não pode ser no futuro';
    return false;
  }

  erro.textContent = '';
  return true;
}

function validarTipoJogador() {
  const erro = document.getElementById('erroTipo');
  const selecionado = document.querySelector('input[name="tipoJogador"]:checked');
  
  if (!selecionado) {
    erro.textContent = 'Selecione um tipo de jogador';
    return false;
  }
  
  erro.textContent = '';
  return true;
}

function validarDecks() {
  const erro = document.getElementById('erroDecks');
  const selecionados = document.querySelectorAll('input[name="decks"]:checked');
  
  if (selecionados.length === 0) {
    erro.textContent = 'Selecione pelo menos um deck';
    return false;
  }
  
  erro.textContent = '';
  return true;
}

function validarRaridade(raridade) {
  const erro = document.getElementById('erroRaridade');
  
  if (raridade === '') {
    erro.textContent = 'Selecione uma raridade';
    return false;
  }
  
  erro.textContent = '';
  return true;
}

function validarFormulario() {
  const nome = document.getElementById('nome').value;
  const idade = document.getElementById('idade').value;
  const dataCadastro = document.getElementById('dataCadastro').value;
  const raridade = document.getElementById('raridade').value;

  const validacoes = [
    validarNome(nome),
    validarIdade(idade),
    validarData(dataCadastro),
    validarTipoJogador(),
    validarDecks(),
    validarRaridade(raridade)
  ];

  return validacoes.every(v => v === true);
}

function coletarDados() {
  const decks = Array.from(document.querySelectorAll('input[name="decks"]:checked'))
    .map(checkbox => checkbox.value);

  return {
    nome: document.getElementById('nome').value.trim(),
    idade: parseInt(document.getElementById('idade').value),
    dataCadastro: document.getElementById('dataCadastro').value,
    tipo: document.querySelector('input[name="tipoJogador"]:checked').value,
    deckFavorito: decks.join(', '),
    raridade: document.getElementById('raridade').value,
    comentario: document.getElementById('comentarios').value.trim()
  };
}

async function enviarFormulario(dados) {
  console.log('Enviando dados para a API:', dados);
  try {
    const response = await fetch(`${API_BASE_URL}/usuarios`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dados)
    });

    if (response.status === 201 || response.ok) {
      return {
        sucesso: true,
        mensagem: 'Cadastro realizado com sucesso! Bem-vindo ao Yu-Gi-Oh!'
      };
    } else if (response.status === 400) {
      const erro = await response.json();
      return {
        sucesso: false,
        mensagem: 'Dados inválidos: ' + (erro.mensagem || 'Verifique os campos')
      };
    } else {
      return {
        sucesso: false,
        mensagem: 'Erro ao enviar cadastro. Tente novamente.'
      };
    }
  } catch (erro) {
    console.error('Erro na requisição:', erro);
    return {
      sucesso: false,
      mensagem: 'Erro de conexão. Verifique se o servidor está ativo.'
    };
  }
}

function exibirMensagem(sucesso, texto) {
  mensagemDiv.textContent = texto;
  mensagemDiv.className = 'mensagem ' + (sucesso ? 'sucesso' : 'erro');
  mensagemDiv.style.display = 'block';
  
  setTimeout(() => {
    mensagemDiv.style.display = 'none';
  }, 5000);
}

function limparFormulario() {
  formCadastro.reset();
  document.querySelectorAll('.error').forEach(el => el.textContent = '');
}

formCadastro.addEventListener('submit', async (e) => {
  e.preventDefault();

  if (!validarFormulario()) {
    exibirMensagem(false, 'Corrija os erros do formulário');
    return;
  }

  const dados = coletarDados();
  const resultado = await enviarFormulario(dados);
  
  exibirMensagem(resultado.sucesso, resultado.mensagem);


  
  if (resultado.sucesso) {
    limparFormulario();
  }
});