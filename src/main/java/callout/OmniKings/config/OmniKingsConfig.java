package callout.OmniKings.config;

import JinRyuu.JRMCore.JRMCoreH;
import callout.OmniKings.core.OmniKingsCoreH;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class OmniKingsConfig {
    public static void init(Configuration config)
    {
        config.load();

        int[] i = { 200, 350, 500};
        Property prop = null;

        //Racial Skill TPs Costs
        prop = config.get("OmniKings Server Sided Configs", "Racial Skill Bio-Android TP costs", i, "Server Sided! Bio-Android main skill costs changable.", 1, 1000000);
        JRMCoreH.vlblRSklsLvl[5] = prop.getIntList();
        JRMCoreH.vlblRSklsLvlO[5] = prop.getIntList();

        //Racial Skill Mind Requirement
        double[] i2 = { 1.0D };
        prop = config.get("OmniKings Server Sided Configs", "Racial Skill Bio-Android Mind Requirement", i2, "Server Sided! Mind Requirement is percentage based. Values can be from 0.005 to 100 (default: 1.0)", 0.005D, 100.0D);
        JRMCoreH.vlblRSklsMR[5] = JRMCoreH.frmt_d(prop.getDoubleList(), 0.005D, 100.0D);
        JRMCoreH.vlblRSklsMRO[5] = JRMCoreH.frmt_d(prop.getDoubleList(), 0.005D, 100.0D);

        //Bio Android form power up
        i = new int[] { 100, 140, 190, 210, 260};
        String[] s = new String[i.length];
        for (int j = 0; j < s.length; j++) {
            s[j] = (JRMCoreH.trans[5][j] + " " + i[j]);
        }
        prop = config.get("OmniKings Server Sided Configs", "Racial Skill Bio-Android - Damage multiplier", s, "Server Sided! The numbers are meant to be in percentage with the minimum at 10% and maximum at 100,000%. Change only to your own responsibility! Having too high multiplier will cause glitches!");

        float[][] flt2 = new float[i.length][6];
        String[] sa = new String[i.length];
        int len = prop.getStringList().length;
        for (int j = 0; j < i.length; j++) {
            for (int j2 = 0; j2 < 6; j2++)
            {
                int k = len > j ? Integer.parseInt(prop.getStringList()[j].split(" ")[1]) : i[j];
                k = k > 100000 ? 100000 : k < 10 ? 10 : k;
                i[j] = k;
                flt2[j][j2] = (k * ((j == 3) && (j2 == 1) ? 0.88F : 1.0F) * 0.01F);
                sa[j] = (s[j].split(" ")[0] + " " + (len > j ? prop.getStringList()[j].split(" ")[1] : Integer.valueOf(i[j])));
            }
        }
        OmniKingsCoreH.TransBaStBnP = flt2;
        OmniKingsCoreH.TransBaStBnPO = flt2;
        prop.set(sa);

        //Bio-Android Ki Regeneration
        i = new int[] { 100, 100, 100, 100, -50};
        s = new String[i.length];
        for (int j = 0; j < s.length; j++) {
            s[j] = (JRMCoreH.trans[5][j] + " " + i[j]);
        }
        prop = config.get("OmniKings Server Sided Configs", "Racial Skill Bio-Android - Ki Regeneration multipliers", s, "Server Sided! The numbers are meant to be in percetage with the minimum at -1000% and maximum at 1000% The negatives mean those are the transformation ki costs! Also percentages are still based on Power bonus amount.");

        float[] flt = new float[i.length];
        sa = new String[i.length];
        len = prop.getStringList().length;
        for (int j = 0; j < i.length; j++)
        {
            flt[j] = (i[j] * 0.01F);
            sa[j] = (s[j].split(" ")[0] + " " + (len > j ? prop.getStringList()[j].split(" ")[1] : Integer.valueOf(i[j])));
        }
        OmniKingsCoreH.TransBaRgn = flt;
        OmniKingsCoreH.TransBaRgnO = flt;
        prop.set(sa);

        config.save();
    }
}