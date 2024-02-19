describe('Calculator Tests', () => {
    beforeEach(()=>{
      cy.visit('/calculator')
    })
    it('calcuator loads', ()=>{
      cy.contains('Calculator')
    })
    it('calculator adds two numbers together', ()=>{
      cy.get('#display').should('have.text', '0')
      cy.get('.btn').contains('2').click()
      cy.get('.btn.operator-side').contains('+').click()
      cy.get('.btn').contains('2').click()
      cy.get('.btn').contains('=').click()
      cy.get('#display').should('contain.text', '4')
    })
    it('C button clears text', ()=>{
      cy.get('#display').should('have.text', 0)
      cy.get('.btn').contains('2').click()
      cy.get('#display').should('have.text', 2)
      cy.get('.btn').contains('C').click()
      cy.get('#display').should('have.text', 0)
    }) 
})
  