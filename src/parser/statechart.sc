SYSTEM : sys{
	
	STATE : s1{
		DESC : d1
		INPUT : i1,i2,i3
		CLICKABLE : c1,c2
	}
	STATE : s2{
		DESC : d2
		INPUT : i4,i5
		CLICKABLE : c3,c4,c5
	} 
	
	TRANSITION : t1{
		SOURCE : s1
		DEST : s2
		TRIGGER : c2
		GUARD : t=10
		ACTI : t=11
		  }
}
