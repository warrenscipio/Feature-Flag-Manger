const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: './src/index.js',
    output: {
        path: path.join(__dirname, '/dist'),
        filename: 'bundle.js'
    },
    watch: true,
    module: {
        rules:[
            {
                test: /\.js$/,
                exclude:/node_modules/,
                use:[
                    {loader:'babel-loader'}
                ]
            },
            {
                test: /\.css/,
                exclude:/node_modules/,
                use:[
                    {loader:'style-loader'},{loader:'css-loader'}
                ]
            }
        ]
    },
    devServer: {
        contentBase: path.join(__dirname, "/dist"),
        compress: true,
        port: 8081,
        proxy: {
            '/': {
                target: 'http://localhost:8080',
                secure: false,
                changeOrigin: true
            }
        }
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html'
        })
    ]
}