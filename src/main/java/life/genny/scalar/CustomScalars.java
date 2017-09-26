package life.genny.scalar;

import java.util.Map;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class CustomScalars {
	
	public static GraphQLScalarType CustGQLMap = new GraphQLScalarType("Map", "Built-in String", new Coercing<Map<String, Object>, Map<String, Object>>() {
        @Override
        public Map<String, Object> serialize(Object input) {
            return (Map<String, Object>) input;
        }

        @Override
        public Map<String, Object> parseValue(Object input) {
            return serialize(input);
        }

        @Override
        public Map<String, Object> parseLiteral(Object input) {
            if (!(input instanceof StringValue)) return null;
            return null;
        }
    });

}
