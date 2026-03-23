const API = window.location.origin;

async function carregarPerfil(){
    try {
        const res = await fetch(`${API}/api/perfis/1`);
        const perfil = await res.json();


        document.getElementById('nome').textContent = perfil.nome;
        document.getElementById('titulo').textContent = perfil.titulo;
        document.getElementById('bio').textContent = perfil.bio;
        document.getElementById('logo').textContent = perfil.logo;
        document.title = `Portfolio - ${perfil.nome}`;

    }   catch (e) {
        console.error('Erro ao carregar perfil', e);
    }

}

async function carregarProjetos() {
  try {
    const res = await fetch(`${API}/api/projetos/perfil/1`);
    const projetos = await res.json();
    const container = document.getElementById('lista-projetos');

    if (projetos.length === 0) {
      container.innerHTML = '<p class="loading">Nenhum projeto cadastrado ainda.</p>';
      return;
    }

    container.innerHTML = projetos.map(p => `
      <div class="card">
        <h3>${p.titulo}</h3>
        <p>${p.descricao}</p>
        <div class="techs">${p.tecnologias}</div>
        <div class="card-links">
          ${p.linkGithub ? `<a href="${p.linkGithub}" target="_blank">GitHub</a>` : ''}
          ${p.linkDeploy ? `<a href="${p.linkDeploy}" target="_blank">Deploy</a>` : ''}
        </div>
      </div>
    `).join('');
  } catch (e) {
    console.error('Erro ao carregar projetos', e);
  }
}

async function carregarHabilidades() {
  try {
    const res = await fetch(`${API}/api/habilidades/perfil/1`);
    const habilidades = await res.json();
    const container = document.getElementById('lista-habilidades');

    if (habilidades.length === 0) {
      container.innerHTML = '<p class="loading">Nenhuma habilidade cadastrada ainda.</p>';
      return;
    }

    container.innerHTML = habilidades.map(h => `
      <div class="tag">
        ${h.nome}
        <span>${h.nivel}</span>
      </div>
    `).join('');
  } catch (e) {
    console.error('Erro ao carregar habilidades', e);
  }
}

async function carregarExperiencias() {
  try {
    const res = await fetch(`${API}/api/experiencias/perfil/1`);
    const experiencias = await res.json();
    const container = document.getElementById('lista-experiencias');

    if (experiencias.length === 0) {
      container.innerHTML = '<p class="loading">Nenhuma experiência cadastrada ainda.</p>';
      return;
    }

    container.innerHTML = experiencias.map(e => `
      <div class="timeline-item">
        <h3>${e.cargo}</h3>
        <div class="empresa">${e.empresa}</div>
        <div class="periodo">${e.dataInicio} → ${e.dataFim}</div>
        <p>${e.descricao}</p>
      </div>
    `).join('');
  } catch (e) {
    console.error('Erro ao carregar experiências', e);
  }
}

async function carregarContatos() {
  try {
    const res = await fetch(`${API}/api/contatos/perfil/1`);
    const contatos = await res.json();
    const container = document.getElementById('lista-contatos');

    if (contatos.length === 0) {
      container.innerHTML = '<p class="loading">Nenhum contato cadastrado ainda.</p>';
      return;
    }

    container.innerHTML = contatos.map(c => `
      <a href="${c.url}" target="_blank" class="contato-item">
        ${c.tipo}
      </a>
    `).join('');
  } catch (e) {
    console.error('Erro ao carregar contatos', e);
  }
}

carregarPerfil();
carregarProjetos();
carregarHabilidades();
carregarExperiencias();
carregarContatos();