package com.example.demo_spring_boot_features.factory;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

@NoArgsConstructor
public class TaxFormulaFactoryBean implements FactoryBean<TaxFormula> {

    private final Logger logger = LoggerFactory.getLogger(TaxFormulaFactoryBean.class);
    private TaxFormula taxFormula;
    private String typeOfTaxFormula;


    /*We can use builder to pass this type of tax formula*/
    @Override
    public TaxFormula getObject() throws Exception {
        switch (typeOfTaxFormula) {
            case "S":
                return new ScotlandRateFormula();
            default:
                return new NoTaxFormula();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return ScotlandRateFormula.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
