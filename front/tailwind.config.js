/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/**/*.{html,ts}",
    ],
    theme: {
    extend: {
        colors: {
            'bluefont': '#6C5CCF',
        },
        screens: {
            'mobile': {'max': '639px'},
        }
    },
  },
  plugins: [],
}

