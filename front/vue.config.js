const bootstrapSassAbstractsImports = require('vue-cli-plugin-bootstrap-vue/sassAbstractsImports.js')
module.exports = {
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/': ''
        },
        ws: false
      }
    }
  }

}
