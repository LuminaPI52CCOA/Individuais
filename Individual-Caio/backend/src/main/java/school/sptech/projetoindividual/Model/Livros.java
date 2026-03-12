    package school.sptech.projetoindividual.Model;

    public class Livros {
        private Integer id;
        private String titulo;
        private String autor;

        private Integer fkCategoria;
        private String genero;

        public Livros() {
        }

        public Livros(String titulo, String autor, Integer fkCategoria) {
            this.titulo = titulo;
            this.autor = autor;
            this.fkCategoria = fkCategoria;
        }

        public String getTitulo() {
            return titulo;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public Integer getFkCategoria() {
            return fkCategoria;
        }

        public void setFkCategoria(Integer fkCategoria) {
            this.fkCategoria = fkCategoria;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }
    }