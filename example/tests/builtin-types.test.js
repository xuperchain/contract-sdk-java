var assert = require("assert");

var codePath = "../builtin-types/target/builtin-types-0.2.0-jar-with-dependencies.jar";

Test("caller", function (t) {
    var features1 = xchain.Deploy({
        name: "features1",
        code: codePath,
        lang: "java",
        init_args: {},
        type: "native",
        options: { "account": "XC1111111111111111@xuper" }
    })
    var features2 = xchain.Deploy({
        name: "features2",
        code: codePath,
        lang: "java",
        init_args: {},
        type: "native",
        options: { "account": "XC1111111111111111@xuper" }
    })
    resp = features1.Invoke("Call", {
        "contract": "features2",
        "method": "Caller",
        "module": "native",
    })
    assert.equal(resp.Body, "features1")
})
