/*
* Copyright (C) 2015 RegExpTask Project by Ihor Dynka
 */

package com.softserveinc.edu.ita.dao_jdbc.interfaces;

import java.io.Serializable;

public interface IIdentified<PK extends Serializable> {
    public PK getId();
}
