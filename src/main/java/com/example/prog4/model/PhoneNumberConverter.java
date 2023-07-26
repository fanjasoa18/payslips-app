package com.example.prog4.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

public class PhoneNumberConverter implements Converter<String, List<PhoneNumber>> {
    @Override
    public List<PhoneNumber> convert(String source) {
        List<PhoneNumber> phoneNumbersList = new ArrayList<>();
        String[] phoneNumberArray = source.split(",");

        for (String phoneNumber : phoneNumberArray) {
            PhoneNumber phoneNumberObj = new PhoneNumber();
            phoneNumberObj.setPhoneNumbers(phoneNumber.trim());
            // Vous pouvez également définir d'autres propriétés de l'objet PhoneNumber si nécessaire
            phoneNumbersList.add(phoneNumberObj);
        }

        return phoneNumbersList;
    }
}
