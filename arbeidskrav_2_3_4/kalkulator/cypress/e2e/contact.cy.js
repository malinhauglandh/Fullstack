describe('contact form tests', ()=>{
    beforeEach(()=>{
        cy.visit('/contact')
    })
    it('finds components', ()=>{
        cy.get('#contact-form').should('exist')
        cy.get('#name').should('exist')
        cy.get('#email').should('exist')
        cy.get('#feedback').should('exist')
        cy.get('#submit-button').should('exist')
    })

    it('submit button is disabled', ()=>{
        cy.get('#submit-button').should('be.disabled')
    })

    it('submit button is enabled after correct input', ()=>{
        cy.get('#name').type('Test E2E')
        cy.get('#email').type('test@test.com')
        cy.get('#feedback').type('This is a test to see if the button is not disabled')
        cy.get('#submit-button').should('not.be.disabled')
    })

    it('submit returns failure when server isnt running',  ()=>{
        cy.get('#name').type('Test Submit')
        cy.get('#email').type('test@test.com')
        cy.get('#feedback').type('This is a test to see if submitting displays a response')
        cy.get('#submit-button').click()
        cy.wait(1000)
        cy.get('#submit-success').should('not.exist')
        // teit test 
    })

    it('submit returns success when server is running',  ()=>{
        cy.get('#name').type('Test Submit')
        cy.get('#email').type('test@test.com')
        cy.get('#feedback').type('This is a test to see if submitting displays a response')

        //mocker successful return
        cy.intercept("POST", "http://localhost:3000/feedbacks", {
            statusCode:201,
        }).as("submitData")

        cy.get('#submit-button').click()
        
        cy.wait("@submitData").then((intercept)=>{
            expect(intercept.response.statusCode).to.equal(201)
        })
    })
})