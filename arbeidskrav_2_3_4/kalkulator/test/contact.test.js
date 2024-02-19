import { mount } from '@vue/test-utils';
import Contact from '@/components/Contact.vue';
import { test, expect, beforeEach } from 'vitest';
import store from '../src/store/store' // Import the Vuex store instance directly
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

describe('Contact', () => {
    let wrapper = null;

    beforeEach(() => {
        wrapper = mount(Contact, { 
            global: {
                plugins: [store],
            },
            components: {
              FontAwesomeIcon // Include FontAwesome as a component
            }
          });

    });

    it('updates the store when input fields are changed', async () => {
        // Simulate user input and trigger change event
        await wrapper.find('#name').setValue('John Doe');
        await wrapper.find('#email').setValue('john@example.com');
        await wrapper.find('#feedback').setValue('Great service!');

        // Check if store state is updated
        expect(store.state.name).toBe('John Doe');
        expect(store.state.email).toBe('john@example.com');
        expect(store.state.feedback).toBe('Great service!');

    });

    it('submits the form if all fields are correctly', async () => {
        await wrapper.find('#name').setValue('Ola Nordmann');
        await wrapper.find('#email').setValue('ola@nordmann.no');
        await wrapper.find('#feedback').setValue('Hei, dette er en test');
        await wrapper.find('#submit-button').trigger('click')

        // Check if the form submission flag is set to true
        expect(wrapper.vm.submitted).toBe(true);
    });
});
