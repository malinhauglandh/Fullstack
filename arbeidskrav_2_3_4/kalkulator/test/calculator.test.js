import { mount } from '@vue/test-utils';
import Calculator from '@/components/Calculator.vue';
import { test, expect } from 'vitest';

describe('Calculator', () => {
    it('displays the number 0', async () => {
        const wrapper = mount(Calculator);
        expect(wrapper.find('.display').text()).toBe('0');
    });

    test('button-clicks', () => {
        const wrapper = mount(Calculator);
        const button = wrapper.find('.btn');
        button.trigger('click');
        expect(wrapper.emitted('click'))
    });

    test('addition', async () => {
        const wrapper = mount(Calculator);

        const number2 = wrapper.findAll('.btn').filter((btn) => btn.text() === '2').at(0);
        const number5 = wrapper.findAll('.btn').filter((btn) => btn.text() === '5').at(0);
        const equal = wrapper.findAll('.btn').filter((btn) => btn.text() === '=').at(0);
        const plus = wrapper.findAll('.btn').filter((btn) => btn.text() === '+').at(0);
        const display = wrapper.find('.display');

        expect(display.text()).toBe('0');

        await number5.trigger('click');
        await plus.trigger('click');
        await number2.trigger('click');
        await equal.trigger('click');

        expect(wrapper.vm.current).toBe('7');
        expect(display.text()).toBe('7');
    });

    test('subtraction', async () => {
        const wrapper = mount(Calculator);

        const number2 = wrapper.findAll('.btn').filter((btn) => btn.text() === '2').at(0);
        const number5 = wrapper.findAll('.btn').filter((btn) => btn.text() === '5').at(0);
        const equal = wrapper.findAll('.btn').filter((btn) => btn.text() === '=').at(0);
        const minus = wrapper.findAll('.btn').filter((btn) => btn.text() === '-').at(0);
        const display = wrapper.find('.display');

        expect(display.text()).toBe('0');

        await number5.trigger('click');
        await minus.trigger('click');
        await number2.trigger('click');
        await equal.trigger('click');

        expect(wrapper.vm.current).toBe('3');
        expect(display.text()).toBe('3');
    });

    test('multiplication', async () => {
        const wrapper = mount(Calculator);

        const number2 = wrapper.findAll('.btn').filter((btn) => btn.text() === '2').at(0);
        const number5 = wrapper.findAll('.btn').filter((btn) => btn.text() === '5').at(0);
        const equal = wrapper.findAll('.btn').filter((btn) => btn.text() === '=').at(0);
        const multi = wrapper.findAll('.btn').filter((btn) => btn.text() === 'x').at(0);
        const display = wrapper.find('.display');

        expect(display.text()).toBe('0');

        await number5.trigger('click');
        await multi.trigger('click');
        await number2.trigger('click');
        await equal.trigger('click');

        expect(wrapper.vm.current).toBe('10');
        expect(display.text()).toBe('10');
    });




});
