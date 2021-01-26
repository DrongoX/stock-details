import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url '/trading-companies/ERROR'
        headers {
            accept(applicationJson())
        }
    }

    response {
        status INTERNAL_SERVER_ERROR()
    }
}
