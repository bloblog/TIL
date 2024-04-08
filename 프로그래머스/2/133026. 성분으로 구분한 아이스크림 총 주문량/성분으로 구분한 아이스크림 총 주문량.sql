SELECT ingredient_type, sum(total_order) as TOTAL_ORDER
FROM first_half a
    JOIN icecream_info b
    ON a.flavor = b.flavor
GROUP BY 1
ORDER BY 2;