/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/main/resources/templates/**/*.html', // Путь к Thymeleaf-шаблонам
    './src/main/resources/static/**/*.js',     // JS-файлы в static
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

