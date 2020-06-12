module.exports = {
    publicPath: '',
    outputDir: '../main/resources/static',
    chainWebpack(config) {
        config.output.filename("js/App.js");
    },
};
