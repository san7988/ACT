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
	STATE : s3{
		DESC : d3
		INPUT : i4,i5
		CLICKABLE : c3,c4,c5
	}
	STATE : s4{
		DESC : d4
		INPUT : i4,i5
		CLICKABLE : c3,c4,c5
	}
	STATE : s5{
		DESC : d5
		INPUT : i4,i5
		CLICKABLE : c3,c4,c5
	}
	STATE : s6{
		DESC : d5
		INPUT : i4,i5
		CLICKABLE : c3,c4,c5
	}
	TRANSITION : t0{
		SOURCE : s1
		DEST : s2
		TRIGGER : c2
		GUARD : t=10
		ACTI : t=11
		  }
	TRANSITION : t1{
		SOURCE : s1
		DEST : s3
		TRIGGER : c2
		GUARD : t=10
		ACTI : t=11
		  }
	TRANSITION : t2{
		SOURCE : s1
		DEST : s4
		TRIGGER : c3
		GUARD : t=10
		ACTI : t=11
		  }
	TRANSITION : t3{
		SOURCE : s2
		DEST : s5
		TRIGGER : c3
		GUARD : t=10
		ACTI : t=11
		  }
	TRANSITION : t4{
		SOURCE : s4
		DEST : s6
		TRIGGER : c3
		GUARD : t=10
		ACTI : t=11
		  }
	TRANSITION : t5{
		SOURCE : s6
		DEST : s3
		TRIGGER : c3
		GUARD : t=10
		ACTI : t=11
		  }
}
