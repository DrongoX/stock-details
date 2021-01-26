import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description('Should GET Trading Company by ticker')
    request {
        method GET()
        url '/trading-companies/NOTFOUND'
        headers {
            accept(applicationJson())
        }
    }

    response {
        status NOT_FOUND()
    }
}
