exports.config = {

    seleniumAddress: 'http://localhost:4444/wd/hub',
    baseUrl: 'http://localhost:9000/',
	specs: ['todo-spec.js'],

    jasmineNodeOpts: {
        showColors: true,
        isVerbose: true
    }
};