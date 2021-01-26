import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Should GET Trading Company by ticker')
    request {
        method GET()
        url $(consumer(regex('/trading-companies/[a-zA-Z0-9]+')))
        headers {
            accept(applicationJson())
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body([
                id: anyNonBlankString(),
                description: regex('.*'),
                ticker: anyAlphaNumeric()
        ])
    }
}
